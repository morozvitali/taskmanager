✅ Тестування TaskService у Spring Boot
1️⃣ Ми використовуємо Mockito, щоб створити мок-об'єкт репозиторію (TaskRepository).
2️⃣ Перед кожним тестом (@BeforeEach) ми ініціалізуємо taskService з мок-репозиторієм.
3️⃣ Ми тестуємо три методи:

getTasks() – перевіряємо, чи повертається список завдань.
addTask() – перевіряємо, чи save() викликається рівно 1 раз.
deleteTask() – перевіряємо, чи deleteById() викликається при правильному ID.


✅ Тестування TaskController у Spring Boot
Оскільки TaskController відповідає за обробку HTTP-запитів, для його тестування ми використаємо MockMvc – спеціальний інструмент для перевірки веб-запитів без запуску реального сервера.
1️⃣ @WebMvcTest(TaskController.class) – запускає тест лише для контролера (без бази даних).
2️⃣ @MockBean private TaskService taskService; – створює мок-об'єкт TaskService, щоб контролер не звертався до реальної бази.
3️⃣ Тест testGetTasks():

Симулює виклик taskService.getTasks().
Виконує GET /tasks.
Перевіряє, чи повертається правильна HTML-сторінка з правильними даними.
4️⃣ Тест testAddTask():
Виконує POST /tasks.
Перевіряє, чи викликався taskService.addTask().
Переконується, що після додавання відбувається редірект.
5️⃣ Тест testDeleteTask():
Виконує POST /delete-task.
Перевіряє, чи викликався taskService.deleteTask().
Очікує редірект після видалення.

✅ Тестування TaskRepository з H2
Створимо TaskTest.java

✅ Додамо негативні тести
📌 Оновимо TaskServiceTest.java
🔹 Цей тест перевіряє, що метод deleteTask(99L) не зламає програму, якщо ID не існує

✅ Запустити всі тести: mvn test