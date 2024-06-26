package assertk.coroutines.assertions

import assertk.Assert
import assertk.assertions.support.appendName

/**
 * Returns an assert that asserts on the result of the given suspend call.
 *
 * @param name The name of the suspend function you are calling.
 * @param extract The suspend function to extract the property value out of the value of the current assert.
 *
 * ```
 * assertThat(person).having("name()", { it.name() }).isEqualTo("Sue")
 * ```
 */
suspend fun <T, P> Assert<T>.having(name: String, extract: suspend (T) -> P): Assert<P> =
    transform(appendName(name, separator = ".")) { extract(it) }

@Deprecated(
    message = "Function suspendCall has been renamed to having",
    replaceWith = ReplaceWith("having(name, extract)"),
    level = DeprecationLevel.WARNING
)
suspend fun <T, P> Assert<T>.suspendCall(name: String, extract: suspend (T) -> P): Assert<P> =
    transform(appendName(name, separator = ".")) { extract(it) }
