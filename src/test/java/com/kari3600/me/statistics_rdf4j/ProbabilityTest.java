package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbabilityTest {
    @Test
    public void testEarthquakesCasualtiesGreaterThan1000() {
        assertEquals(0.225, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?casualties > 1000) AS ?prob)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testEarthquakesCasualtiesGreaterThan1000IfMagnitudeGreaterThan8() {
        assertEquals(0.333, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?casualties > 1000, ?magnitude > 8) AS ?prob)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties ;
                    dbp:magnitude ?magnitude .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testUniversitiesStudentsGreaterThan10000() {
        assertEquals(0.3300970873786408, query1Group1Var("universities.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?students > 10000) AS ?prob)
                WHERE {
                    ?university a dbo:University ;
                    dbo:numberOfStudents ?students .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testUniversitiesEndowmentGreaterThan1e9() {
        assertEquals(0.13268608414239483, query1Group1Var("universities.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?endowment > 1000000000) AS ?prob)
                WHERE {
                    ?university a dbo:University ;
                    dbo:endowment ?endowment .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testCitiesPopulationGreaterThan100000() {
        assertEquals(0.12573099415204678, query1Group1Var("cities.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?population > 100000) AS ?prob)
                WHERE {
                    ?city a dbo:City ;
                    dbo:populationTotal ?population .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testAirportsElevationGreaterThan1000() {
        assertEquals(0.09601634320735444, query1Group1Var("airports.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?elevation > 1000) AS ?prob)
                WHERE {
                    ?airport a dbo:Airport ;
                    dbo:elevation ?elevation .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testAirportsElevationGreaterThan1000IfRunwayGreaterThan5000() {
        assertEquals(0.14153132250580047, query1Group1Var("airports.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?elevation > 1000, ?runway > 5000) AS ?prob)
                WHERE {
                    ?airport a dbo:Airport ;
                    dbo:elevation ?elevation ;
                    dbp:r1LengthF ?runway .
                }
                """, "prob").doubleValue(), 0.001);
    }
}
