// "Make constructor parameter a private property" "true"

class A(foo: String) {
    fun bar() {
        val a = foo<caret>
    }
}