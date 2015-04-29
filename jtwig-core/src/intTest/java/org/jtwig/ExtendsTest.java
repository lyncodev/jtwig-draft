package org.jtwig;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExtendsTest extends AbstractIntegrationTest {

    @Test
    public void simpleExtends() throws Exception {
        JtwigTemplate template = defaultStringTemplate("{% extends 'classpath:/example/extends/extendable-template.twig' %}{% block one %}Override{% endblock %}");

        String result = template.render(JtwigModel.newModel());

        assertThat(result, is("Override"));
    }

    @Test
    public void nestedExtends() throws Exception {
        JtwigTemplate template = defaultStringTemplate("{% extends 'classpath:/example/extends/nested-extendable-template.twig' %}{% block one %}Override{% endblock %}");

        String result = template.render(JtwigModel.newModel());

        assertThat(result, is("Override"));
    }

    @Test
    public void nestedExtendsWithoutOverride() throws Exception {
        JtwigTemplate template = defaultStringTemplate("{% extends 'classpath:/example/extends/nested-extendable-template.twig' %}");

        String result = template.render(JtwigModel.newModel());

        assertThat(result, is("One"));
    }
}
