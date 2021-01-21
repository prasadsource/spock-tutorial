package com.spocktests

import com.learn.Colour
import com.learn.Palette
import com.learn.Polygon
import com.learn.Renderer
import com.learn.ShapeFactory
import com.learn.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject


class SpockTest1 extends Specification {
    def "should be a simple assertion"() {
        expect:
        1==1
    }

    def "should demonstrate given-when-then"() {
        given:
        def polygon = new Polygon(4);

        when:
        int count = polygon.numberOfSides

        then:
        count ==4

    }
    def "should expect exceptions"(){
        when:
            new Polygon(0)
        then:
        def exception = thrown(TooFewSidesException.class)
        exception.numberOfSides == 0
    }

    //Data Pipes
    def "should expect an exception for the number of invalid inputs"(){
        when:
         new Polygon(shapes)
        then:
        thrown(TooFewSidesException.class)
        where:
        shapes << [4,-1,0,1]
    }

    def "data tables to expect  max"() {
        expect:
        Math.max(a,b) == max
        where:
        a | b   | max
        1 | 3   | 3
        1 | 4   | 6
    }
    def "should able to create mock for concrete class"(){
        given:
        Renderer renderer = Mock()
        @Subject
        def poloygon = new Polygon(4,renderer)
        when:
        poloygon.draw()
        then:
        4* renderer.drawLine()
    }

    def "should be able to create a stub"(){
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red
        def renderer = new Renderer(palette);
        when:
        def color =renderer.getForegroundColour()
        then:
        color==Colour.Red
    }

    def "helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer);
        when:
            def polygon = shapeFactory.createDefaultPolygon()
        then:
        checkDefaultShape(polygon,renderer)
    }

    //with stops execution if fist case fails
    def "with method test me"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer);
        when:
        def polygon = shapeFactory.createDefaultPolygon()
        then:
        with(polygon) {
            polygon.numberOfSides !=4
            polygon.renderer == renderer;
        }
    }
//verify Checks all tests scenarios even one case fails
        def "verify method test me"() {
            given:
            Renderer renderer = Mock()
            def shapeFactory = new ShapeFactory(renderer);
            when:
            def polygon = shapeFactory.createDefaultPolygon()
            then:
            verifyAll(polygon) {
                polygon.numberOfSides != 4
                polygon.renderer == null;
            }
        }

    def checkDefaultShape(Polygon polygon, Renderer renderer) {
        polygon.numberOfSides !=4
        polygon.renderer != renderer;
        renderer==null;

    }
}
