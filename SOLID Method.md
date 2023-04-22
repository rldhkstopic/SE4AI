# SOLID Method

소유자: 익명
최종 편집 일시: 2023년 4월 22일 오후 11:29

객체지향 프로그래밍에서 SOLID 원칙은 Class와 Object를 설계하는데 도움이 되는 5가지 원칙을 의미합니다. 다음과 같은 5개 방법이 있으며, 하나씩 같이 살펴봅시다.

1. Single Responsibility Principle (SRP)
2. OCP
3. s
4. s
5. s

### 1. Single Responsibility Principle (SRP)

> ***“한 클래스는 단 하나의 책임만 가져야 한다.”***
> 

Class가 하나의 작업을 수행하고, 그 작업을 수행하기 위해 필요한 메서드와 속성만을 가지도록 해야 합니다. 다르게 말하자면, Class가 여러 책임을 갖게 되면, 한 가지 변경 사항으로 인해 다른 부분에 영향을 미칠 가능성이 있기 때문에 SRP 원칙을 준수해야 합니다. 

SRP를 지키는 Class는 변경 사항이 발생하더라도 영향을 받는 부분이 제한되므로 코드의 유지 보수성을 기대할 수 있습니다.

아래 예시 코드를 통해 살펴 봅시다.

```java
class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def get_area(self):
        return self.width * self.height

    def save_to_database(self):
        # (데이터베이스에 사각형을 저장하는 코드)
        pass
```

먼저 SRP를 지키지 않은 예시입니다.

사각형의 면적을 계산하는 `get_area()` Method와 데이터베이스에 사각형을 저장하는 `save_to_database()` 메서드가 함께 있는 것을 볼 수 있습니다. 이렇게 하면 두 가지 책임을 가지고 있기 때문에 SRP를 지키지 않은 것이라고 볼 수 있습니다. 

```java
class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def get_area(self):
        return self.width * self.height

class RectangleDB:
    def save_to_database(self, rectangle):
        # 데이터베이스에 사각형을 저장하는 코드
        pass
```

다음은 SRP를 지킨 예시입니다.

`Rectangle` Class는 사각형의 속서오가 면적을 계산하는 `get_area()` Method만을 가지고 있으며, `RectangleDB` Class는 데이터베이스에 사각형을 저장하는 책임을 가지고 있습니다. 이렇게 함으로써 각 클래스가 단 하나의 책임만을 가지게 되므로 SRP를 지키는 것입니다.

### 2. Open-Closed Principle (OCP)

> ***Software Entity(Class, Module, Function, etc.)는 확장에는 열려 있으나(open), 변경에는 닫혀 있어야 한다(close)***
> 

소프트웨어를 수정하지 않고도 새로운 동작을 추가할 수 있도록 하는 원칙입니다.

예를 들어, 새로운 기능을 추가하기 위해서는 기존의 코드를 수정하지 않고도 새로운 코드를 추가하여 이를 구현할 수 있어야 합니다. 

아래 예시 코드를 통해 살펴 봅시다.

```java
class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def get_area(self):
        return self.width * self.height

class Circle:
    def __init__(self, radius):
        self.radius = radius

    def get_area(self):
        return 3.14 * self.radius * self.radius

# 새로운 모양(예: 삼각형)이 추가되면, 기존의 코드를 변경해야 함
class Triangle:
    def __init__(self, base, height):
        self.base = base
        self.height = height

    def get_area(self):
        return 0.5 * self.base * self.height
```

먼저 OCP를 지키지 않은 예시입니다.

`Rectangle`과 `Circle` Class에 `get_area()` Method가 있지만, 새로운 모양이 추가되면 새로운 클래스를 만들어 반복되게 `get_area()` Method를 구현해야 합니다. 

```java
class Shape:
    def get_area(self):
        pass

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def get_area(self):
        return self.width * self.height

class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius

    def get_area(self):
        return 3.14 * self.radius * self.radius

class Triangle(Shape):
    def __init__(self, base, height):
        self.base = base
        self.height = height

    def get_area(self):
        return 0.5 * self.base * self.height
```

그러나 두 번째 예시 코드에서는 `Shape` Class를 만들어 `get_area()` Method를 가지도록하고, 각 도형 클래스는 `Shape` Class를 상속하여 `get_area()` 메서드를 구현합니다. 

이렇게 하면 새로운 모양이 추가되더라도 `Shape` Class를 상속하는 새로운 클래스를 만들어 `get_arear()` Method를 구현하면 되기 때문에, 기존의 코드를 변경할 필요가 없어 OCP를 지키게 되는 것입니다. 

이처럼 OCP는 코드를 변경할 필요 없이 새로운 동작을 추가할 수 있도록 해서 `유연성`과 `확장성`을 높이고, 코드의 `재사용성`과 `유지 보수성`을 향상시킵니다. 

## 3. Liskov Substitution Principle (LSP)

> ***서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다.***
> 

기반 클래스로부터 파생된 서브 클래스는 기반 클래스가 정의한 Interface를 따르고, 기반 클래스에서 가능한 작업은 서브 클래스에서도 가능해야 함을 의미합니다.

예시 코드를 통해 LSP를 알아봅시다.

```java
class Rectangle {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
```

`Rectangle` Class는 `getWidth()`와 `getHeight()` Method를 가지고 있으며, `Square` Class는 `Rectangle` Class를 상속받습니다. 

그러나 `Square` Class에서는 `setWidth()`와 `setHeight()` Method를 오버라이딩하여 사각형의 너비와 높이가 같아지도록 구현했습니다. 이렇게 하면 `Square` Class를 `Rectangle` Class로 대체해도 의미가 변하지 않습니다.

예를 들어, 다음과 같이 `Rectangle` Class의 Instance를 `Square` Class의 Instance로 대체해도 `getArea()` Method의 결과는 같습니다.

```java
Rectangle rectangle = new Rectangle();
rectangle.setWidth(5);
rectangle.setHeight(10);
System.out.println(rectangle.getArea()); // 결과: 50

Square square = new Square();
square.setWidth(5);
square.setHeight(5);
System.out.println(square.getArea()); // 결과: 25

rectangle = square;
System.out.println(rectangle.getArea()); // 결과: 25
```

`Square` Class는 `Rectangle` Class의 서브 타입으로 적절하게 동작하며, LSP를 지키는 것이 됩니다.

## 4. Interface Segregation Principle (ISP)

> ***클라이언트가 자신이 사용하지 않는 메서드에 의존하지 않아야 한다.***
> 

클라이언트가 필요로 하지 않는 인터페이스의 메서드를 구현하지 않도록 하는 것입니다.

이것은 인터페이스를 작게 분리하여 하나의 인터페이스가 너무 많은 책임을 지지 않도록 하는 것을 목적으로 하고 있습니다.

다음은 ISP를 적용한 예시 코드입니다.

```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void sendFax();
}

class MultiFunctionPrinter implements Printer, Scanner, Fax {
    @Override
    public void print() {
        // 인쇄를 위한 코드
    }

    @Override
    public void scan() {
        // 스캔을 위한 코드
    }

    @Override
    public void sendFax() {
        // 팩스를 보내기 위한 코드
    }
}

class SimplePrinter implements Printer {
    @Override
    public void print() {
        // 인쇄를 위한 코드
    }
}
```

`Printer` Interface는 `print()` Method만을 가지고 있습니다.

그러나 `Scanner`와 `Fax` Class는 각각 `scan()`과 `sendFax()` Method를 가지고 있습니다.

`Printer` Inteface는 `print()` Method만을 가지고 있으므로, `Printer` Interface를 구현한 `SimplePrinter` Class는 `print()` Method만을 가족 있습니다. 또한, `MultiFunctionPrinter` Class는 `Printer, Scanner, Fax` Interface를 모두 구현하고 있으므로, `print(), scan(), sendFax()` Method를 가지고 있습니다.

이렇게 하면 클라이언트는 자신이 필요로 하는 Interface만 구현한 Class를 사용할 수 있으므로, ISP를 지키게 되는 것입니다.

ISP를 따르면 인터페이스가 더 작고 단순해지므로, Class 간의 결합도가 낮아지고 유지 보수성이 향상됩니다. 또한, Interface를 작게 분리하면 클래스의 재사용성이 높아지므로, 코드의 효율성도 향상될 수 있습니다.

## 5. Dependency Inversion Principle (DIP)

> ******************************************************************************************************************************************추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다.******************************************************************************************************************************************
> 

높은 수준의 Module이 낮은 수준의 Module에 의존하면 안 된다는 것을 의미합니다.

대신, 모든 Module은 추상화된 Interface에 의존해야 하며, 저수준의 Module은 추상화된 인터페이스를 구현하도록 해야 합니다.

아래는 DIP를 적용한 예시 코드입니다.

```java
interface Database {
    void connect();
    void disconnect();
    void query(String sql);
}

class OracleDatabase implements Database {
    @Override
    public void connect() {
        // 오라클 데이터베이스에 연결하는 코드
    }

    @Override
    public void disconnect() {
        // 오라클 데이터베이스와의 연결을 해제하는 코드
    }

    @Override
    public void query(String sql) {
        // 오라클 데이터베이스에서 쿼리를 실행하는 코드
    }
}

class MySQLDatabase implements Database {
    @Override
    public void connect() {
        // MySQL 데이터베이스에 연결하는 코드
    }

    @Override
    public void disconnect() {
        // MySQL 데이터베이스와의 연결을 해제하는 코드
    }

    @Override
    public void query(String sql) {
        // MySQL 데이터베이스에서 쿼리를 실행하는 코드
    }
}

class Application {
    private final Database database;

    public Application(Database database) {
        this.database = database;
    }

    public void run() {
        database.connect();
        database.query("SELECT * FROM users");
        database.disconnect();
    }
}
```

`Database` Interface는 `connect()`, `disconnect()`, `query()` Method를 가지고 있습니다. 그리고 `OracleDatabase` Class와 `MySQLDatabase` Class는 각각 `Database` Interface를 구현합니다. 

`Application` Class는 `Database` Interface 사용하여 데이터베이스에 연결하고, 쿼리를 실행합니다.

`Application` Class는 생성자에서 `Database`Interface를 받아서 이를 사용하여 데이터베이스에 연결하고, 쿼리를 실행하며, 연결을 해제합니다. `OracleDatabase` Class와 `MySQLDatabase` Class는 각각 `Database` Interface를 구현하므로, `Application` Class는 `Database` Interface만을 사용하여 데이터베이스와 상호작용합니다. 

이렇게 하면 `Application` Class는 `Database` Interface에만 의존하므로, 저수준의 모듈인 `OracleDatabase` 클래스와 `MySQLDatabase` 클래스와의 결합도가 낮아지게 됩니다. 

이것은 `Application` 클래스가 데이터베이스의 종류에 대해 알 필요가 없으며, 데이터베이스의 종류가 변경되더라도 `Application` 클래스를 변경하지 않아도 된다는 것을 의미합니다. 즉, `Database` 인터페이스를 통해 느슨하게 결합되어 있으므로, 유연성과 확장성이 높아지며, 유지 보수성과 재사용성도 향상됩니다.

이렇듯 DIP는 추상화된 인터페이스를 사용하여 모듈 간의 결합도를 낮추고, 유연성과 확장성을 높이는 것을 목적으로 합니다.