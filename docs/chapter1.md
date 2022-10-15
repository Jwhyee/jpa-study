# 1강(JPA 소개)
관계형 데이터 베이스
- 관계형 데이터 베이스는 가장 대중적이고 신뢰할 만한 안전한 데이터 저장소
- 데이터베이스에 데이터를 관리하기 위해서는 `SQL`을 사용
    - Java 애플리케이션은 `JDBC API`를 사용하여 `SQL`을 `DB`에 전달

## 1-1. SQL을 직접 다룰 때 발생하는 문제점
### 반복적인 문제 발생
> 현재까지 개발이 된 테이블(객체)은 `Member` 하나 뿐이다.<br>
> `Member`를 위한 DAO에 작성된 것은 현재 `find()`, `save()` 뿐이지만, 개발이 더 진행된다면 `update()`, `remove()` 등을 추가적으로 작성해주어야 한다.<br>
> 만약 테이블이 더 많아진다면 우리가 구현해야할 메소드는 더 많아질 것이고, 반복적인 메소드를 계속해서 개발해주어야 한다.

### SQL에 의존적인 개발
> `Member`, `MemberDAO`를 만든 뒤 개발을 진행하면서 느낀 것은 굉장히 번거롭다.<br>
> 현재 `Member`에는 id, name을 제외한 `field`는 없다. 하지만 추후에 이외 필드가 추가된다면 `DAO`에 해당 필드들을 계속해서 추가를 해주어야 한다.
```java
// TEL 컬럼을 추가하기 전
String sql_ver1 = "SELECT MEMBER_ID, NAME FROM MEMBER M WHERE MEMBER_ID = ?";

// TEL 컬럼을 추가한 후
String sql_ver2 = "SELECT MEMBER_ID, NAME, TEL FROM MEMBER M WHERE MEMBER_ID = ?";
```

## 1-2. 패러다임의 불일치
### 상속 상태의 저장
> 어플리케이션을 개발하면서 복잡성은 더욱 커진다.<br>
> 단순 객체의 경우 모든 속성 값을 꺼내 저장하면 되지만, 상속 혹은 참조의 형태를 띄고 있다면, 상태를 저장하기 쉽지 않다.<br>
> 현재 개발되어 있는 `Member`를 저장할 때 `Team` 또한 저장을 해주어야하는 것과 같다.<br>

```java
public class Item {
    Long id;
    String name;
    int price;
}

class Album extends Item {
    String artist;
}
```
- 객체는 상속이라는 기능을 가지고 있지만, `Table` 관점에서 보면 상속이라는 기능이 없다.
- 위 객체를 저장하기 위해서는 아래와 같은 쿼리문을 작성해야한다.
```sql
INSERT INTO ITEM ...
INSERT INTO ALBUM ...
```
- `Album`을 조회한다고 가정한다면 `Item`, `Album`을 `Join`하여 `Album` 객체를 생성해야한다.
> 즉, 이런 과정이 모두 패러다임의 불일치를 해결하려고 소모하는 비용이다.<br>
> 이런 객체를 DB가 아닌 `Collection`에 보관한다면 쉽게 저장할 수 있다.
```java
list.add(album);
```

### JPA를 통한 해결
> 위와 같은 패러다임의 불일치 문제를 JPA가 해결해준다.