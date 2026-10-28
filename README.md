# Triangle Classifier

A command-line application that accepts three numbers representing the side lengths of a triangle and classifies it as **Equilateral**, **Isosceles**, or **Scalene**.

Side lengths must be positive, finite numbers and satisfy the triangle inequality. Invalid input is reported on standard error.

The project keeps domain rules independent from command-line concerns. The CLI creates domain objects, invokes classification, and presents the result.

## Domain-Driven Design

- **Domain:** Models a valid triangle and enforces its side-length invariants. Classification rules map a triangle to its type and display label.
- **CLI adapter:** Parses command-line arguments, invokes the domain behavior, and writes results or errors to the console.
- **Entry point:** Starts the CLI adapter. The application is small enough that it does not currently need separate application or infrastructure layers.

## Assumptions

- The CLI receives exactly three values accepted by Java's `Double.parseDouble`, including scientific notation.
- Side lengths must be positive and satisfy the strict triangle inequality. Degenerate (flat) triangles, where two sides sum exactly to the third, are rejected.
- `double` uses binary floating-point, so many decimal fractions are approximate. Equality and near-degenerate cases are evaluated using the parsed binary values, not exact decimal arithmetic.
- Values that overflow to infinity or underflow to zero are rejected as nonfinite or nonpositive.
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

- Use `BigDecimal` if exact decimal behavior is required, with explicit input limits to avoid excessive resource use for extreme values.
- Consider Lombok (for example, `@UtilityClass`) if utility-class boilerplate grows enough to justify the extra dependency and annotation processing.
