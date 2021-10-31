package kotlin_test

/*
in package,
Functions, properties, classes, objects, and interfaces can be declared at the "top-level" directly inside a package
최상위 선언이란 파일 최상위에 선언되는 클래스, 메서드, 변수를 뜻한다.

public ->  default, which means that your declarations will be visible everywhere.
private -> it will only be visible inside the file that contains the declaration.
internal ->  it will be visible everywhere in the same module.
protected -> is not available for top-level declarations.

 */
public var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in this file
fun function() {
    // 이렇게 파일 최상위에 함수가 선언이 가능하다.
}
private fun privateFunc() {

}
class VisibilityModifier {
    protected fun test() {
        privateFunc() // ok . because same file
        bar = 1 // 같은 파일이니까 변경할 수 있다.
    }
}
class B {
    fun test() {
        val a = VisibilityModifier()
        //a.test() // no.
    }
}

/*
class members
public ->  any client who sees the declaring class sees its public members.
private -> means that the member is visible inside this class only (including all its members).
internal -> means that any client inside this module who sees the declaring class sees its internal members.
protected -> means that the member has the same visibility as one marked as private, but that it is also visible in subclasses.
protected private과  동일한 가시성을 갖지만 하위 클래스에서도 볼 수 있음 !
 */