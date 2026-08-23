# User stories

> Functional requirements for the initial version of SegundUM, expressed as user stories.

## Index

| ID | Title | Status |
|----|-------|--------|
| [US-01](#us-01--user-registration) | User registration | Included |
| [US-02](#us-02--updating-personal-details) | Updating personal details | Pending |
| [US-03](#us-03--creating-a-product) | Creating a product | Included |
| [US-04](#us-04--editing-products) | Editing products | Included |
| [US-05](#us-05--pickup-location) | Pickup location | Included |
| [US-06](#us-06--monthly-product-summary) | Monthly product summary | Pending |
| [US-07](#us-07--filtered-product-search) | Filtered product search | Included |
| [US-08](#us-08--loading-categories) | Loading categories | Pending |
| [US-09](#us-09--editing-categories) | Editing categories | Pending |

> Stories marked as *Pending* are out of scope for this initial version and will be addressed in future iterations ([ibracb/segundum](https://github.com/ibracb/segundum)).

---

## US-01: User registration

As a **user**, I want to register in the application so that I can access its features.

### Acceptance criteria

- First name, last name and password are required; if any is missing or blank, a validation message is shown and the user is not registered.
- The email must have a valid format.
- An email already registered in the system cannot be used again.
- The date of birth must be valid and in the past.
- The phone number must be exactly nine digits.
- After a successful registration, the session starts automatically and the user is redirected to the main page.

---

## US-02: Updating personal details

As a **user**, I want to update my personal details to keep my information up to date.

### Acceptance criteria

- An authenticated user can update their first name, last name, password, date of birth and phone number.
- Each field is only updated if the new value is valid (first name, last name and password not blank; date parseable; phone number with nine digits).
- If the user does not exist, no update is performed.

> Note: the business logic exists in the service layer; the user interface is still pending.

---

## US-03: Creating a product

As a **user**, I want to create a product to put an item up for sale.

### Acceptance criteria

- Title and description are required; if either is missing, a validation message is shown.
- The price must be a valid number greater than or equal to zero.
- A product condition and an existing category must be specified.
- The product is associated with the authenticated user as its seller, with an automatic publication date and a view counter set to zero.
- After a successful creation, a summary of the created product is shown and the form is cleared.

---

## US-04: Editing products

As a **user**, I want to edit my products to update their price and/or description so that the information stays up to date.

### Acceptance criteria

- Only the description and the price of a product can be edited.
- The edit form is pre-filled with the current values of the product.
- The price, if provided, must be a valid number greater than or equal to zero; if left blank, the current price is retained.
- The update is confirmed with a message and can be cancelled to return to the list of my products.

---

## US-05: Pickup location

As a **user**, I want to associate a pickup location with a product I have put up for sale to make handover easier.

### Acceptance criteria

- The pickup location consists of a description, a longitude and a latitude.
- If delivery is not available, the pickup location is mandatory: description, longitude and latitude must all be provided.
- Longitude must be between -180 and 180, and latitude between -90 and 90; neither value may be 0.
- If delivery is not available and the pickup location is invalid, the product is not created and an explanatory error message is shown.

---

## US-06: Monthly product summary

As a **user**, I want to obtain a monthly summary of my products for sale and their views.

### Acceptance criteria

- The summary is obtained for a specific month and year.
- It includes the products put up for sale in that period together with their accumulated view counts.

> Note: the business logic exists in the service layer; the user interface is still pending.

---

## US-07: Filtered product search

As a **user**, I want to browse products for sale filtering by description, category, condition and price to find items that interest me.

### Acceptance criteria

- All four filters are optional and can be combined: description, category, condition and maximum price.
- The category filter includes the descendant subcategories of the selected category.
- The condition filter offers the six available conditions (new, as new, good condition, acceptable, for parts and to be repaired).
- There is an action to clear the filters that restores the full list of products.
- When opening the detail view of a product from the list, its view counter is incremented.

---

## US-08: Loading categories

As an **administrator**, I want to load new categories to classify products.

### Acceptance criteria

- Only an administrator user can load categories.
- Categories are loaded from an XML file with a hierarchical structure (categories and subcategories).
- If the hierarchy already exists (same root identifier), it is not loaded again.
- In this initial version, loading happens automatically on application start-up from the `Multimedia.xml` file included in the project.

> Note: automatic loading covers the feature in this version; the administration interface is still pending.

---

## US-09: Editing categories

As an **administrator**, I want to edit existing categories to add a description.

### Acceptance criteria

- Only an administrator user can edit categories.
- Editing consists of assigning a description to an existing category.
- The description is required; if it is not valid, no update is performed.
- If the category does not exist, no update is performed.

> Note: the business logic exists in the service layer; the user interface is still pending.