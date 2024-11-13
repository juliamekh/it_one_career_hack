package com.itonehack.smartbudget.application.service.category;

import com.itonehack.smartbudget.domain.exception.BadRequestException;
import com.itonehack.smartbudget.domain.exception.ForbiddenException;
import com.itonehack.smartbudget.domain.exception.NotFoundException;
import com.itonehack.smartbudget.domain.model.Budget;
import com.itonehack.smartbudget.domain.model.Category;
import com.itonehack.smartbudget.domain.model.User;
import com.itonehack.smartbudget.domain.ports.in.BudgetService;
import com.itonehack.smartbudget.domain.ports.in.CategoryService;
import com.itonehack.smartbudget.domain.ports.in.UserService;
import com.itonehack.smartbudget.domain.ports.out.CategoryRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link CategoryService}
 */
@Component
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final UserService userService;
    private final BudgetService budgetService;

    public CategoryServiceImpl(
            CategoryRepositoryPort categoryRepositoryPort,
            UserService userService,
            @Lazy BudgetService budgetService
    ) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.userService = userService;
        this.budgetService = budgetService;
    }

    @Override
    public List<Category> findCategoriesByBudgetId(String username, Long budgetId) {
        logger.info("Finding categories by budget id: {}", budgetId);
        Budget budget = budgetService.findBudgetById(budgetId);
        if (!budget.getUser().getUsername().equals(username)) {
            throw new BadRequestException("You can't get categories from someone else's budget");
        }
        return categoryRepositoryPort.findCategoriesByBudgetId(budgetId);
    }

    @Override
    public Category save(String username, String category) {
        logger.info("Creating category if not exists for user with username: {} with name: {}", username, category);
        User foundUser = userService.findByUsername(username);
        Category categoryEntity = categoryRepositoryPort.findCategoryByName(category);
        if (categoryEntity == null) {
            Category categoryForSave = Category.builder()
                    .name(category)
                    .forEveryone(false)
                    .build();
            categoryEntity = categoryRepositoryPort.save(categoryForSave);
            categoryRepositoryPort.tieUpCategoryToUser(categoryEntity.getId(), foundUser.getId());
        } else {
            if (notExistsCategoryByIdForUserId(categoryEntity.getId(), foundUser.getId())) {
                categoryRepositoryPort.tieUpCategoryToUser(categoryEntity.getId(), foundUser.getId());
            } else {
                throw new BadRequestException("Category already exists");
            }
        }
        return categoryEntity;
    }

    @Override
    public List<Category> findCategoriesByUsername(String username) {
        logger.info("Finding all categories for user with username: {}", username);
        User user = userService.findByUsername(username);
        return categoryRepositoryPort.findCategoriesByUserId(user.getId());
    }

    @Override
    public Category findCategoryById(Long id) {
        logger.info("Finding category with ID {}", id);
        return categoryRepositoryPort.findById(id)
                .orElseThrow(() -> {
                    String message = "A category with id: " + id + " not exists";
                    logger.error(message);
                    return new NotFoundException(message);
                });
    }

    @Override
    public void deleteCategory(String username, Long categoryId) {
        logger.info("Deleting category with id: {} for user with username: {}", categoryId, username);
        Category foundCategory = findCategoryById(categoryId);
        User foundUser = userService.findByUsername(username);
        if (notExistsCategoryByIdForUserId(foundCategory.getId(), foundUser.getId())) {
            throw new ForbiddenException("It is forbidden to update someone else's category");
        }
        categoryRepositoryPort.deleteCategory(foundCategory);
    }

    @Override
    public List<Category> findCategoriesByForEveryone(boolean forEveryone) {
        logger.info("Finding categories for everyone");
        return categoryRepositoryPort.findCategoriesByForEveryone(forEveryone);
    }

    @Override
    public boolean notExistsCategoryByIdForUserId(Long id, Long userId) {
        logger.info("Checking if category with ID {} exists for user with ID {}", id, userId);
        return categoryRepositoryPort.notExistsCategoryByIdForUserId(id, userId);
    }
}
