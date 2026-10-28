# Triangle Classifier

A command-line application that accepts three numbers representing the side lengths of a triangle and classifies it as **Equilateral**, **Isosceles**, or **Scalene**.

Side lengths must be positive finite numbers and satisfy the triangle inequality. Invalid input is reported on standard error.

The project keeps domain rules independent from command-line concerns. The CLI creates domain objects, invokes classification, and presents the result.

## Domain-Driven Design

- **Domain:** Models a valid triangle and enforces its side-length invariants. Classification rules map a triangle to its type and display label.
- **CLI adapter:** Parses command-line arguments, invokes the domain behavior, and writes results or errors to the console.
- **Entry point:** Starts the CLI adapter. The application is small enough that it does not currently need separate application or infrastructure layers.

## Assumptions

- The CLI receives exactly three values that Java can parse as `double` values.
- Side lengths must be positive, finite, and satisfy the strict triangle inequality.
- Side equality is exact; no rounding tolerance is applied.
- Classification is based only on side lengths, not angles or units. Equilateral means all three sides are equal, isosceles means exactly two are equal, and scalene means all three differ.

## Package and Run from a JAR

From the `triangle` directory, build the executable JAR and run it with three side lengths:

```bash
mvn test
mvn clean package
java -jar target/triangle-1.0-SNAPSHOT.jar 3 4 5
```

The example prints `Scalene`.

## Possible Future Improvements

- Use `BigDecimal` instead of `double` if exact decimal precision or a wider numeric range is required; this would also change input parsing and the domain's arithmetic and equality rules.
- Consider Lombok (for example, `@UtilityClass`) if utility-class boilerplate grows enough to justify the extra dependency and annotation processing.
