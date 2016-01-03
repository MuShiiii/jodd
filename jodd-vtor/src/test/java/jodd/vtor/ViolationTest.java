// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.vtor;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class ViolationTest {

    @Test
    public void testConstructor1() throws Exception {
        //given
        Object validatedObject = new Object();
        Object invalidValue = new Object();

        //when
        Violation violation = new Violation("niceViolation", validatedObject, invalidValue);

        //then
        assertEquals("validatedObject must be equal to a validatedObject which was given to constructor", violation.getValidatedObject(), validatedObject);
        assertEquals("invalidValue must be equal to a invalidValue which was given to constructor", violation.getInvalidValue(), invalidValue);
        assertNull("violation must be null", violation.getCheck());
        assertNull("constraint must be null", violation.getConstraint());
    }

    @Test
    public void testConstructor2() throws Exception {
        //given
        Object validatedObject = new Object();
        Object invalidValue = new Object();
        ValidationConstraint constr = mock(ValidationConstraint.class);
        Check niceCheck = new Check("niceCheck", constr);

        //when
        Violation violation = new Violation("niceViolation", validatedObject, invalidValue, niceCheck);

        //then
        assertEquals("name must be equal to a name of violation which was given to constructor", violation.getName(), "niceViolation");
        assertEquals("ValidatedObject must be equal to a ValidatedObject which was given to constructor", violation.getValidatedObject(), validatedObject);
        assertEquals("InvalidValue must be equal to a InvalidValue which was given to constructor", violation.getInvalidValue(), invalidValue);
        assertEquals("Check must be equal to a Check which was given to constructor", violation.getCheck(), niceCheck);
        assertEquals("Constraint must be equal to a Constraint which was given to constructor", violation.getConstraint(), constr);
    }


    @Test
    public void testToString() throws Exception {
        //given
        Object validatedObject = new Object();
        Object invalidValue = new Object();
        Violation violation = new Violation("niceViolation", validatedObject, invalidValue, new Check("niceCheck", new TestValidationConstraint()));

        //when
        String toString = violation.toString();

        //then
        assertEquals(toString, "Violation{niceViolation:jodd.vtor.ViolationTest$TestValidationConstraint}");
    }

    private static class TestValidationConstraint implements ValidationConstraint {
        @Override
        public void configure(Annotation annotation) {

        }

        @Override
        public boolean isValid(ValidationConstraintContext vcc, Object value) {
            return false;
        }
    }
}