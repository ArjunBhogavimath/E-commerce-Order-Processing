# 🛒 E-commerce Order Processing System (Spring Boot)

This project implements a backend service to manage and process orders for an e-commerce platform. It demonstrates clean architecture, modular design, asynchronous queue processing, and metrics tracking — all built using Java Spring Boot, PostgreSQL, and in-memory queues.

---

## 🚀 Features

- RESTful API to accept and track orders
- Asynchronous in-memory queue to simulate background order processing
- Real-time order status updates: `PENDING`, `PROCESSING`, `COMPLETED`
- Metrics endpoint for:
  - Total orders processed
  - Average processing time
  - Count of orders by status

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot
- **Database:** PostgreSQL (or SQLite/MySQL supported)
- **Queue:** In-memory `BlockingQueue`
- **Build Tool:** Maven
- **Other:** JPA/Hibernate, REST API, Docker (optional)

---

## 📦 API Endpoints

### ➕ Create Order

**POST** `/orders`

**Request Body:**
```json
{
  "userId": "123e4567-e89b-12d3-a456-426614174000",
  "itemIds": ["item1", "item2"],
  "totalAmount": 250.75
}
```

**Response:**
```json
{
  "orderId": "generated-order-id",
  "userId": "123e4567-e89b-12d3-a456-426614174000",
  "itemIds": ["item1", "item2"],
  "totalAmount": 250.75,
  "status": "PENDING",
  "createdAt": "2025-07-16T10:30:00"
}
```

---

### 📊 Get Order Status

**GET** `/orders/{orderId}/status`

**Response:**
```json
"PROCESSING"
```

---

### 📈 Metrics Endpoint

**GET** `/orders/metrics`

**Response:**
```json
{
  "totalOrders": 1000,
  "averageProcessingTimeMs": 4832.35,
  "pendingOrders": 10,
  "processingOrders": 8,
  "completedOrders": 982
}
```

---

## 🗃️ Database Schema

```sql
CREATE TABLE orders (
  order_id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  item_ids TEXT[],
  total_amount DOUBLE PRECISION NOT NULL,
  status VARCHAR(20),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  completed_at TIMESTAMP
);
```

---

## ⚙️ Run the Project Locally

### Prerequisites

- Java 17+
- PostgreSQL (or SQLite/MySQL)
- Maven

### Steps

```bash
# Clone the repo
git clone https://github.com/your-username/ecommerce-order-processing.git
cd ecommerce-order-processing

# Update DB credentials in application.properties

# Build and run
./mvnw spring-boot:run
```

---

## 📁 Project Structure

```
src/main/java/
├── controller/
│   └── OrderController.java
├── entity/
│   └── Order.java
├── repository/
│   └── OrderRepository.java
├── service/
│   └── OrderService.java
├── queue/
│   └── OrderQueue.java
└── enums/
    └── OrderStatus.java
```

---

## ✅ Design Decisions

- Used in-memory `BlockingQueue` for async simulation.
- Orders are processed in a separate thread to mimic background workers.
- Processing time is simulated using `Thread.sleep(5000)` for realism.
- Metrics calculated on-demand without caching for simplicity.
- Used UUID for order and user IDs to simulate distributed environments.

---

## 📌 Assumptions

- Item details are stored elsewhere; only `itemIds` are tracked here.
- Single-threaded queue processor for simplicity.
- Database writes are synchronous and immediate.

---

## 🙋‍♂️ Author

Mallikarjunaiah B M  
📧 vpmallikarjuna03@gmail.com  
📱 +91 7619413484  
🔗 https://www.linkedin.com/in/mallikarjunaiah-b-m-1331a319a/
