# spring-cloud-issue-882

Reproduces an issue regarding [spring cloud contract](https://spring.io/projects/spring-cloud-contract).

To reproduce the error, execute the test `./gradlew generateContractTests`

You'll see something like this:
```
> Task :generateContractTests
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
[Fatal Error] :1:1: Content is not allowed in prolog.
```

See spring cloud contract issue [#882](https://github.com/spring-cloud/spring-cloud-contract/issues/882)
