package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentileTest {
    @Test
    public void testEarthquakesMagnitude() {
        assertEquals(7.9, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?magnitude, 0.9) AS ?percentile)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:magnitude ?magnitude .
                }
                """, "percentile").floatValue(), 0.1);
        assertEquals(5.8, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?magnitude, 0.1) AS ?percentile)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:magnitude ?magnitude .
                }
                """, "percentile").floatValue(), 0.1);
    }

    @Test
    public void testCarsLength() {
        assertEquals(1988.0, query1Group1Var("cars.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?length, 0.5) AS ?percentile)
                WHERE {
                    ?car a dbo:Automobile ;
                    dbp:length ?length .
                }
                """, "percentile").floatValue(), 20.0); // exact median 1988; impl is approximate
    }

    @Test
    public void testFilmsRuntime() {
        assertEquals(6300.0, query1Group1Var("films.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?runtime, 0.5) AS ?percentile)
                WHERE {
                    ?film a dbo:Film ;
                    dbo:runtime ?runtime .
                }
                """, "percentile").floatValue(), 150.0); // exact median; impl is approximate
    }

    @Test
    public void testAirportsElevation() {
        assertEquals(214.884, query1Group1Var("airports.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?elevation, 0.5) AS ?percentile)
                WHERE {
                    ?airport a dbo:Airport ;
                    dbo:elevation ?elevation .
                }
                """, "percentile").floatValue(), 20.0); // exact median; impl is approximate
    }

    @Test
    public void testMountainsElevation() {
        assertEquals(2524.963, query1Group1Var("mountains.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?elevation, 0.5) AS ?percentile)
                WHERE {
                    ?mountain a dbo:Mountain ;
                    dbo:elevation ?elevation .
                }
                """, "percentile").floatValue(), 100.0); // exact median; impl is approximate
    }
}
