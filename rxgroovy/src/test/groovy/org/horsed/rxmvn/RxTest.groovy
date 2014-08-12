package org.horsed.rxmvn

import static org.junit.Assert.*

import org.junit.Test

class RxTest {

    @Test
    void shouldFilter_1() {
        assertFalse Rx.filter([id: 1])
    }

    @Test
    void shouldNotFilter_2() {
        assertTrue Rx.filter([id: 2])
    }

    @Test
    void shouldSquare() {
        assertEquals 144, Rx.map([value: 12])
    }
}
