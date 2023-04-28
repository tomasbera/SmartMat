package edu.ntnu.idatt2106_2023_06.backend.model;

import edu.ntnu.idatt2106_2023_06.backend.model.fridge.Fridge;
import edu.ntnu.idatt2106_2023_06.backend.model.fridge.FridgeMember;
import edu.ntnu.idatt2106_2023_06.backend.model.recipe.Allergen;
import edu.ntnu.idatt2106_2023_06.backend.model.recipe.Recipe;
import edu.ntnu.idatt2106_2023_06.backend.model.recipe.RecipeAllergen;
import edu.ntnu.idatt2106_2023_06.backend.model.recipe.RecipeAllergenId;
import edu.ntnu.idatt2106_2023_06.backend.model.users.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeAllergenTest {

    @Nested
    class RecipeAllergen_object_with {

        @Test
        void no_arg_constructor_can_be_made() {
            try {
                RecipeAllergen recipeAllergen = new RecipeAllergen();
            } catch (Exception e) {
                fail();
            }
        }

        @Test
        void all_arg_constructor_can_be_made() {
            try {
                RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(), new Recipe(), new Allergen());
            } catch (Exception e) {
                fail();
            }
        }


        @Test
        void builder_can_be_made() {
            try {
                RecipeAllergen recipeAllergen = RecipeAllergen
                        .builder()
                        .id(new RecipeAllergenId())
                        .allergen(new Allergen())
                        .recipe(new Recipe())
                        .build();
            } catch (Exception e) {
                fail();
            }
        }

    }

    @Nested
    class Null_columns_constructors {

        @Test
        void recipe_cannot_be_null(){
            assertThrows(NullPointerException.class, () -> {
                RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(), null, new Allergen());
            });
        }

        @Test
        void allergen_cannot_be_null(){
            assertThrows(NullPointerException.class, () -> {
                RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(), new Recipe(), null);
            });
        }

    }

    @Nested
    class Null_variables{

        @Test
        void recipe_cannot_be_set_to_null(){
            RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(), new Recipe(), new Allergen());
            assertThrows(NullPointerException.class, () -> {
                recipeAllergen.setRecipe(null);
            });
        }

        @Test
        void allergen_cannot_be_set_to_null(){
            RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(), new Recipe(), new Allergen());
            assertThrows(NullPointerException.class, () -> {
                recipeAllergen.setAllergen(null);
            });
        }
    }

    @Nested
    class Getters{

        @Test
        void recipe_getter_returns_correct_value(){
            Recipe recipe = new Recipe(1L, "Grønnsakslasagne med søtpotet, aubergine og grønnkål",
                    "En vegetarisk oppskrift på grønnsakslasagne full av smak. Her er kjøttdeigen " +
                            "byttet ut med søtpotet og aubergine, ostesausen med en blomkålpuré og lasagneplatene " +
                            "med ulike grønnsaker i tynne skiver. Perfekt vegetarlasagne hvor du kan bruke en " +
                            "rekke grønnsaksrester.\n", "Meny", 5, 1, "image.png",
                    50, new ArrayList<>());

            Allergen allergen = new Allergen(1L, "Dairy", new HashSet<>());
            RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(recipe.getRecipeId(), allergen.getAllergenId()),
                    recipe, allergen);

            assertEquals(recipe, recipeAllergen.getRecipe());
        }

        @Test
        void allergen_getter_returns_correct_value(){
            Recipe recipe = new Recipe(1L, "Grønnsakslasagne med søtpotet, aubergine og grønnkål",
                    "En vegetarisk oppskrift på grønnsakslasagne full av smak. Her er kjøttdeigen " +
                            "byttet ut med søtpotet og aubergine, ostesausen med en blomkålpuré og lasagneplatene " +
                            "med ulike grønnsaker i tynne skiver. Perfekt vegetarlasagne hvor du kan bruke en " +
                            "rekke grønnsaksrester.\n", "Meny", 5, 1, "image.png",
                    50, new ArrayList<>());

            Allergen allergen = new Allergen(1L, "Dairy", new HashSet<>());
            RecipeAllergen recipeAllergen = new RecipeAllergen(new RecipeAllergenId(recipe.getRecipeId(), allergen.getAllergenId()),
                    recipe, allergen);

            assertEquals(allergen, recipeAllergen.getAllergen());
        }
    }
}
