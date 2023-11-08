package ar.com.sicos.model;

import ar.com.sicos.exceptions.DivisionPorZeroException;

import java.util.Optional;

public enum Operation implements Solve {

    SUMA {
        @Override
        public int apply(int v1, int v2) {
            return v1 + v2;
        }
    },

    RESTA {
        @Override
        public int apply(int v1, int v2) {
            return v1 - v2;
        }
    },

    PRODUCTO {
        @Override
        public int apply(int v1, int v2) {
            return v1 * v2;
        }
    },

    DIVISION {
        @Override
        public int apply(int v1, int v2) {
            return Optional.of(v2)
                    .filter(v -> v != 0)
                    .map(v -> v1 / v)
                    .orElseThrow(DivisionPorZeroException::new);
        }
    }
}
