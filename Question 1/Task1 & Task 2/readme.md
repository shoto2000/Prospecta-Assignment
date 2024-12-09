# How to Run the Program

## Clone the Repository
Clone the project to your local machine.

## Open the Project in IntelliJ IDEA
1. Launch IntelliJ IDEA.
2. Open the project by selecting the `pom.xml` file in the project directory.

## Build the Project
- IntelliJ will automatically import dependencies from `pom.xml`.
- If not, right-click on the `pom.xml` file and select **Reload Maven Project**.

## Run the Application
1. Navigate to the main class (annotated with `@SpringBootApplication`).
2. Right-click and select **Run 'MainClassName'**.

## Access the APIs
The application will start on the default port (e.g., `http://localhost:8080`). You can now access the API endpoints.

---

# How to Call the APIs

## Here you can find the already written API's you can easily test
https://www.postman.com/dark-crescent-283785/workspace/publicapis/collection/23490327-e5633352-6501-4809-a81d-d79af17e71f3?action=share&creator=23490327

## Task 1: Fetch Products by Category
- **Endpoint**: GET: `/products/category/{category}`
- **Example**:  
  To fetch products in the "jewelery" category:
  ``` http://localhost:8080/products/category/jewelery```

## Task 2: Add a new Product
- **Endpoint**: POST: `/products`
- Request Body (Example):
```
{
  "title": "New Product",
  "price": 100.0,
  "description": "Description of the new product",
  "category": "electronics",
  "image": "image_url"
}
```
- **Example**:
  ``` http://localhost:8080/products```
