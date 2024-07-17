import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

/*Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
Задача состоит в использовании Stream API и коллекторов для решения следующих задач:
        Создайте список заказов с разными продуктами и их стоимостями.
        Группируйте заказы по продуктам.
        Для каждого продукта найдите общую стоимость всех заказов.
        Отсортируйте продукты по убыванию общей стоимости.
        Выберите три самых дорогих продукта.
        Выведите результат: список трех самых дорогих продуктов и их общая стоимость.*/
public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        // группировка по продуктам и их общая стоимость
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));

        // сортировка продуктов
        List<Map.Entry<String, Double>> sortedProducts = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());


        // выбор трех самых дорогих продуктов
        List<Map.Entry<String, Double>> top3Products = sortedProducts.stream()
                .limit(3)
                .collect(Collectors.toList());

        // вывод результата в консоль
        top3Products.forEach(entry ->
                System.out.println("Изделие: " + entry.getKey() + ", Суммарная стоимость: " + entry.getValue())
        );

    }

}