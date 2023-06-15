# quarkus-datafaker-poc

This project shows how to use [Datafaker](https://www.datafaker.net/) to generate mock data in your quarkus application

> **All the Datafaker related code is in src/test, src/main is there only to provide a codebase to run the tests**

## How to use Datafaker in tests

You can use Datafaker to generate mock data from a plethora of
topics ([full topics list available on the documentation](https://www.datafaker.net/documentation/providers/)).
In order to use it, first add the correct dependency to your pom.xml

```xml

<dependency>
    <groupId>net.datafaker</groupId>
    <artifactId>datafaker</artifactId>
    <version>2.0.0</version>
    <scope>test</scope>
</dependency>
```

> Note that I used scope test because I don't want it in my packaged application

Then, in code, initialize a new Faker

```java
Faker faker=new Faker();
```

you can specify the Locale as well in the constructor, if you need it

```java
Faker faker=new Faker(new Locale("it"));
```

Now you're ready to generate fake data calling some fancy Faker's methods:

```java
User u=new User();
        u.setUsername(faker.name().username());
        u.setFirstName(faker.name().firstName());
        u.setLastName(faker.name().lastName());
        u.setBirthday(faker.date().birthday().toLocalDateTime().toLocalDate());
```

A full list of all the topics is available [in the documentation](https://www.datafaker.net/documentation/providers/)
Furthermore, it is also possible to create custom faker providers,
again [look at the docs](https://www.datafaker.net/documentation/custom-providers/)

## Running the application in test mode

You can run your application in test mode to see it in action with dev services:

```shell script
mvn clean quarkus:test
```

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing
  build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the
  extensions that depend on it.
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code
  for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
- Dev Services Overview ([guide](https://quarkus.io/guides/dev-services)): Enable dev services in order to use docker
  containers to run your application
- Dev Services for Databases ([guide](https://quarkus.io/guides/databases-dev-services)): Enable dev services in order
  to use docker containers to run a database server locally
- Datafaker ([getting started](https://www.datafaker.net/documentation/getting-started/)): Generate fake data
- Datafaker custom providers ([guide](https://www.datafaker.net/documentation/custom-providers/)): Create custom faker
  providers to provide your own fake data