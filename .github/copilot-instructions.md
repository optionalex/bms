# Project Instructions

## Functional Style
- Prefer pure functions: for the same inputs, return the same result without side effects.
- Prefer immutable data. Use `final` for local variables and fields when they should not be reassigned, and use records for suitable immutable data carriers.
- Declare all method, constructor, and lambda parameters `final`, including parameters of the single abstract method in an `@FunctionalInterface`.
- Keep side effects, such as console input/output, at the application boundary; keep domain logic deterministic and independently testable.

## Domain-Driven Design
- Organize Java packages by responsibility under `com.bms.triangle`, using `domain`, `application`, `infrastructure`, and `cli` where they add clarity.
- Keep business rules and domain concepts in `domain`; coordinate use cases in `application`; isolate external concerns in `infrastructure`; handle command-line arguments and output in `cli`.
- Avoid introducing layers or abstractions that do not add value for this small application.

## Project Conventions
- Keep the project compatible with the Java version configured in `triangle/pom.xml`.
- Add or update tests for behavior changes, especially domain rules and invalid input handling.