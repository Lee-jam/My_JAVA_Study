# Wordlist
***
### JAVA word
***
- OOP (Object Oriented Programming language) : 객체지향언어
- 변수(variable) : 하나의 값을 저장하고있는 저장공간(주소지)
 - 지역변수 : 한정되어있는 지역(메소드 등) 내에서만 사용 가능한 변수
 - 전역변수(멤버변수) : 클래스 내에서 사용 가능한 변수
 - 참조변수 : 기본형을 제외한 나머지
 - 매개변수 : 클래스 선언 시 전달인자를 받아 내부로 전달하기 위해 사용하는 변수
 - Static : 프로그램이 메모리 로딩 시 할당하여 공통으로 사용할 수 있는 변수<br/>
(인스턴스와 별개로 사용)
- 상수(constant) : 변수와 마찬가지로 값을 저장하고 있는 저장공간(상수풀)이지만, 상수는 값을 변경할 수 없음.<br/>
```java
int a = 1; //기본적인 변수 선언문 : 자료형 변수명 = 변수값(리터럴) ;
final int SANGSOO = 30; //final 키워드를 사용하고 반드시 초기화 선언을 해야 함. 
//상수의 변수명에는 영문 소문자가 들어갈 수 없음.
```
- 리터럴(literal) : 변수나 상수에 저장되는 값 자체를 의미
- 자료형 기본 타입 : 자료형의 기본타입은 8가지로 존재함.
```java
boolean bool = false; // true 와 false 만 반환 기본 값은 false
byte b = 1;           // 
char c ='a';          //
short s = 2;          //
int i = 5;            //
long l = 7l;          //
float f = 0.1f;       //
double d = 0.5;       //
``` 
```java
/*클래스(Class A)는 객체(Object K)를 만들기 위한 설계도면
K 는 A의 instance
A를 instance화 한 것이 K*/
```

- @Deprecated : annotation으로 사라질 메소드나 필드 앞에 표시되어있고 왜 사라지는지 어떻게 사용하라는 내용이 담겨있음.
- annotation : 크게 3가지 기능이 있음. 주석처리, 코드 추가,
- Method override : 상위클래스가 가지고 있는 메소드를 재정의하는 것, 이 때 메소드명,반환형,매개변수는 같게 하고 내용만 재정의하는 것임.
- Method overload : 클래스 내에 같은 이름의 메서드에 매개변수를 다르게 하여 새로운 기능을 가진 메서드를 구현하는 것
- interface : 상수 + 추상메서드 + default 메서드 + static 메서드 + private 메서드를 가질 수 있는 클래스
- 추상 메서드(abstract method) : 미구현 메서드
- 추상 클래스 : 추상 메서드를 포함하고 있는 클래스; 추상 클래스를 상속 받은 클래스는 반드시 추상 메서드를 재정의해주어야 함.
- 추상 클래스의 객체 생성 방법은 2가지가 있음 : <br/>1. 자식 클래스로 객체 생성을 하고 추상클래스 자료형의 변수에 저장 <br/>2. 익명 클래스를 사용하여 생성
- 메서드
- 스택 : Stack<E> method, peek-엿보기

### Word
- OS (Operation System) : 컴퓨터의 실행환경 서비스를 제공하는 소프트웨어
- 소프트웨어 : 
- 오픈소스 : 
- 프리웨어 :
- 쉐어웨어 :
- 드라이버 :
- 메모리 :
- 컴파일러 : 소스코드를 기계어(바이너리 코드)로 변환하여 컴퓨터가 읽을 수 있도록 변환해주는 시스템
- 인터프리터 : 
- IDE : Intergrated Development Enviorment 통합 개발 환경
- SDK : Software Development Kit 소프트웨어 개발 킷(언어, 컴파일러 등 개발에 필요한 것들이 통합으로 있는 키트)