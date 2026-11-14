package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovarianceTest {
    @Test
    public void testCountryHdiGini() {
        assertEquals(-0.4060886594957206, query1Group1Var("countries.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?hdi, ?gini) AS ?cov)
            WHERE {
                ?city a dbo:Country ;
                dbp:gini ?gini ;
                dbp:hdi ?hdi .
            }
            """, "cov").floatValue(), 0.001);
    }

    @Test
    public void testEarthquakesCasualtiesMagnitude() {
        assertEquals(3367.732443881537, query1Group1Var("earthquakes.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?casualties, ?magnitude) AS ?cov)
            WHERE {
                ?city a dbo:Earthquake ;
                dbp:casualties ?casualties ;
                dbp:magnitude ?magnitude .
            }
            """, "cov").floatValue(), 0.001);
    }

    @Test
    public void testMountainsElevationProminence() {
        assertEquals(298804.99451066746, query1Group1Var("mountains.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?elevation, ?prominence) AS ?cov)
            WHERE {
                ?mountain a dbo:Mountain ;
                dbo:elevation ?elevation ;
                dbo:prominence ?prominence .
            }
            """, "cov").floatValue(), 1.0);
    }

    @Test
    public void testCitiesPopulationArea() {
        assertEquals(929528567689669.8, query1Group1Var("cities.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?population, ?area) AS ?cov)
            WHERE {
                ?city a dbo:City ;
                dbo:populationTotal ?population ;
                dbo:areaTotal ?area .
            }
            """, "cov").floatValue(), 1e12);
    }

    @Test
    public void testCarsLengthWeight() {
        assertEquals(21770421.170564972, query1Group1Var("cars.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?length, ?weight) AS ?cov)
            WHERE {
                ?car a dbo:Automobile ;
                dbp:length ?length ;
                dbp:weight ?weight .
            }
            """, "cov").floatValue(), 1.0);
    }

    @Test
    public void testFilmsBudgetGross() {
        assertEquals(8.987922187408929E17, query1Group1Var("films.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?budget, ?gross) AS ?cov)
            WHERE {
                ?film a dbo:Film ;
                dbo:budget ?budget ;
                dbo:gross ?gross .
            }
            """, "cov").floatValue(), 1e12);
    }
}
