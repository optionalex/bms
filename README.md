# Triangle Classifier

A command-line application that accepts three numbers representing the side lengths of a triangle and classifies it as **Equilateral**, **Isosceles**, or **Scalene**.

Side lengths must be positive finite numbers and satisfy the triangle inequality. Invalid input is reported on standard error.

The domain model and classification rules live in `com.bms.triangle.domain`. The command-line adapter in `com.bms.triangle.cli` parses arguments and prints the result.

## Project Structure

```text
triangle/
	src/main/java/com/bms/triangle/
		App.java                         Application entry point
		cli/TriangleCli.java             Parses arguments and handles console I/O
		domain/Triangle.java             Validated triangle value object
		domain/TriangleClassifier.java   Classifies valid triangles
		domain/TriangleType.java         Classification labels
	src/test/java/com/bms/triangle/
		AppTest.java                     CLI behavior tests
		domain/TriangleTest.java         Triangle invariant tests
		domain/TriangleClassifierTest.java Classification tests
```

Run the application from the `triangle` directory:

```bash
mvn test
mvn compile
java -cp target/classes com.bms.triangle.App 3 4 5
```

The example prints `Scalene`.

## Possible Future Improvements

- Consider Lombok (for example, `@UtilityClass`) if utility-class boilerplate grows enough to justify the extra dependency and annotation processing.
- Split `domain` into `domain.model` and `domain.service` if the domain grows enough for the extra package structure to improve clarity.
