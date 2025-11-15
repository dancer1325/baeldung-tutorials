https://www.baeldung.com/java-19-record-patterns

* goal
  * [JEP-405](https://openjdk.org/jeps/405) preview
    * == record patterns | Java SE 19
      * how to 
        * decompose record values
        * combine record patterns + type patterns

* _Examples:_
  * [GPSPoint.java](../src/main/java/com/baeldung/features/records/GPSPoint.java)
  * [Location.java](../src/main/java/com/baeldung/features/records/Location.java)

* record pattern
  * == 👀construct👀 /
    * allows us to
      * match values -- against a -- record type
      * bind variables -- to the -- corresponding record's components
  * 's identifier
    * == create a variable
    * OPTIONAL
      * if you use it -> simple record pattern
        * ⚠️named record pattern requires Java v21+⚠️
    * allows us to
      * refer -- to the -- record pattern variable

* _Examples:_ 
  * [JEP405RecordPatternsUnitTest.givenNestedRecord_whenTestInstanceOfWithNestedPattern_shouldExtractComponents()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)
  * [JEP405RecordPatternsUnitTest.givenRecord_whenTestRecordPatternBinding_shouldExtractComponents()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)

# `instanceof`

* Pattern Matching for `instanceof`
  * introduced | Java 16
  * allows us to
    * destructuring the object
      * concrete type can be replaced -- by -- `var`
    * simple record pattern
      * == declare a variable DIRECTLY | `instanceof` check
    * | Java 19,
      * ALSO works with records
    * if you use null value -> NOT match any record pattern
    * ALLOWED ALSO | 
      * generic records

* _Examples:_
  * [JEP405RecordPatternsUnitTest.givenObject_whenTestInstanceOfAndCastIdiom_shouldMatchNewInstanceOf()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)
  * [JEP405RecordPatternsUnitTest.givenObject_whenTestDestruct_shouldMatch()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)
  * [JEP405RecordPatternsUnitTest.givenObjectIsNull_whenTestNullCheck_shouldNotMatch()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)
  * [JEP405RecordPatternsUnitTest.givenObject_whenTestGenericTypeInstanceOf_shouldMatch()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)

# `Switch` expression

* accepts
  * ALSO records /
    * ALSO can be destructured
  * guard conditions -- via -- `when`

* _Examples:_
  * [JEP405RecordPatternsUnitTest.givenObject_whenTestSwitchExpressionWithTypePattern_shouldMatch()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)
  * [JEP405RecordPatternsUnitTest.givenObject_whenTestGuardedSwitchExpressionWithTypePattern_shouldMatchAndGuard()](../src/test/java/com/baeldung/features/JEP405RecordPatternsUnitTest.java)

* if you use a sealed interface -> NO require default case
  * _Example:_ [ILocation.java](../src/main/java/com/baeldung/features/records/ILocation.java)
