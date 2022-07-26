
## Prerequisites
In order to be able to setup and run this application, the following need to be installed and setup
- [Android Studio](https://developer.android.com/studio)

## Project Setup

To setup the project in your machine:

- Clone the repo
- Open and Run Project in android studio
- Click on "Edit Configurations option " drop down to switch between task1 and task2 module
- Run the application.


###TASK ONE.

Task one is a simple implementation of a Menu feature.

## Architecture
The project has two packages.
- Model
- Menu

### Model
The model has two data classes *Recipe* and *Subscription*

### Menu
The Menu class exposes a list of recipes available for selection and has a reference to an associated subscription.
The *list of recipes available for selection* and *subscription* is passed to the class through it's constructor.
The class has a list of integers [selectedRecipeIdList] that holds the list of the recipe selected Ids.
To select or unselect a recipe means to add the selected recipe id in the list or remove it.


### Testing
Under test, the [MenuTest] has the Menu class JUnit tests.

- [availableRecipe] is a mock of all available recipes that the user can select
- [nonFamilySubscription] is a mock of a menu associated non family subscription
- [familySubscription] is a mock of a menu associated  family subscription

### TASK TWO.

Task two is a simple single-page application showcasing a list of recipes from an http request.

## Architecture

The project design pattern is MVVM:
- Model
- View
- ViewModel
- Repository
- Api

### Model
- The model classes hold the data used in the `view`classes
- The model has two classes *Recipe* and *Response*
    - [Recipe] holds the recipe data.
    - [Response] holds the network response data which is - If a network response was successful,
      if an exception was thrown and the response data.

### View
- The view classes hold classes that display and present the data, it includes *RecipeAdapter*, *RecipeActivity* .

### ViewModel
- The ViewModel classes *RecipeViewModel* stores and manage UI-related data *recipeLiveData* in a lifecycle conscious way

### Repository
- This class:*RecipeRepository*  handles all logic to do with saving/fetching data from the data sources and provides it to the app.

### Api
- Holds classes that define how network requests are made.
    - [ApiInterface] has methods for making Api calls
    - [ApiClient] defines how network requests are made
    - [NetworkResponse] is a class for displaying the different UI states i.e:
      -*Loading* when a network request is initiated to show the loading spinner.
      -*Success* when a network response is successful to display the recipe data loaded.
      -*Error* when an exception occurs to show an error snack bar in the UI.

### Utils
- These are classes that define a set of methods that perform common, often re-used functions across the app.
    - [Constants] holds data that remains the same when the program is executing
    - [ProgressDialog] holds the progress dialog show and dismiss functions that I can re use across the app.
    - [Utils] has functions such as *getToDate* that can be used across the app

## External Links/dependencies
- [Retrofit] for network requests
- [Glide]for image loading and caching
- [Coroutines]for executing  asynchronous code
- [Timber]for logging


## Task I - Menu Code Kata

### Initial configuration

The idea of the first task is to design a simple implementation of a Menu feature based on the requirements listed below.

For the purpose of this task we will assume the following:<br/> 
A Recipe is a data structure with an id, title, and a list of tags associated with it. Examples of tags can be "hot", "quick", "low-calories", etc.
Recipes are equal if their ids are equal. A Subscription is a data structure with id, delivery day, and isForFamily properties.

You will be creating and adding behaviors to a Menu model:

- A list of recipes available for selection is provided to the menu. 
- Menu exposes a list of recipes available for selection.
- Menu has a reference to an associated subscription.

### Requirements

- Keep SOLID principles in mind while you are working on this task
- Do not develop any UI for this task
- Unit and/or integration tests should be part of this task

**Selecting recipes in the menu**

- You should be able to mark a single recipe as selected.
- You should be able to mark multiple recipes as selected.
- You should be able to ask the menu how many recipes have been selected.

**Unselecting recipes in the menu**

- You should be able to unselect a single selected recipe.
- You should be able to unselect multiple selected recipes.

**Accessing recipes in the menu**

- You should be able to request a list of selected recipes.
- You should be able to request a list of recipes which have a certain tag.

**Restricting the behavior**

- You should not be able to select more than 3 recipes.

**Modifying the behavior based on subscription type**

- You should be able to select up to 5 recipes if the subscription is for a family.
 
*Note*

- Don’t forget to notify calling code about errors where appropriate.
- The Recipe data structure is final. Please do not add more properties to it.
 
 
## Task II -  Recipes List View 


The idea is to create a simple single-page application showcasing the recipes.

### Requirements

- Build a single view application that shows a list of recipes. 
- Implement data loading, basic UI, error handling. 
- Keep in mind code readability, scalability, and maintainability when making implementation decisions. 
- Provide README with justifications and testing strategies. 

The list of recipes should be loaded via HTTP request using this link - [recipes.json](https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json)

**There is no need to build:**<br/>

- Stylish UI, basic and readable is enough,
- Navigation,
- Caching,
- Write UI or unit tests.

**User Interface**

- The app should show a single view.
- At launch, the app should show a loading spinner while it gets the data from the source. 
- When the data is fetched, the app should hide the loading spinner and show the recipes’ view.
- Show the current date in “dd  MMM” format (20 Aug) with text size 18sp as a first element of the recipes list view.
- Recipes in the list are presented as cards with image, title and headline. Title should be in bold with text size 16sp.
- If an error occurred, you should show a snackbar with a short description of the issue.
# RecipeMenu-
# RecipeApp
# RecipeApp
# RecipeApp
# RecipeApp
# RecipeApp
# RecipeApp
