package org.jtwig.model.expression.operation.calculators.binary;

import org.jtwig.configuration.MathContextParameter;
import org.jtwig.context.RenderContext;
import org.jtwig.model.expression.Expression;
import org.jtwig.model.position.Position;
import org.jtwig.util.JtwigValue;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MultiplyOperationCalculatorTest {
    private final RenderContext renderContext = mock(RenderContext.class, RETURNS_DEEP_STUBS);
    private final Position position = mock(Position.class);
    private MultiplyOperationCalculator underTest = new MultiplyOperationCalculator();

    @Before
    public void setUp() throws Exception {
        when(renderContext.configuration().parameter(MathContextParameter.mathContext())).thenReturn(MathContext.DECIMAL32);
    }

    @Test
    public void multiplyWithFloating() throws Exception {
        Expression leftOperand = mock(Expression.class);
        Expression rightOperand = mock(Expression.class);
        when(leftOperand.calculate(renderContext)).thenReturn(new JtwigValue(new BigDecimal("1.3")));
        when(rightOperand.calculate(renderContext)).thenReturn(new JtwigValue(new BigDecimal("2.3")));

        JtwigValue result = underTest.calculate(renderContext, position, leftOperand, rightOperand);

        assertThat(result.asNumber(), is(new BigDecimal("2.99")));
    }
}