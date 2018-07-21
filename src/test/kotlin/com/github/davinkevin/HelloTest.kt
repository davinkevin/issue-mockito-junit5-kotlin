package com.github.davinkevin

import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

class Bar {
    fun call() = 42
}

class Foo(val bar: Bar) {
    fun call() = bar.call() + 23
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class HelloTest {

    @Mock lateinit var bar: Bar
    @InjectMocks lateinit var foo: Foo

    @RepeatedTest(100)
    fun `should return 24`() {
        /* Given */
        whenever(bar.call()).thenReturn(1)
        /* When */
        val r = foo.call()
        /* Then */
        assertThat(r).isEqualTo(24)
    }

    @RepeatedTest(100)
    fun `@InjectMocked foo should have same bar as bar`() {
        assertThat(foo.bar).isSameAs(bar)
    }

}
