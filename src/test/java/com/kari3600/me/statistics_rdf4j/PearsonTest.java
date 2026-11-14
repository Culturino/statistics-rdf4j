package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PearsonTest {
    @Test
    public void testEarthquakesCasualtiesMagnitude() {
        assertEquals(0.1459410339, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?casualties, ?magnitude) AS ?corr)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties ;
                    dbp:magnitude ?magnitude .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testCarsLengthWeight() {
        assertEquals(0.576452497133448, query1Group1Var("cars.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?length, ?weight) AS ?corr)
                WHERE {
                    ?car a dbo:Automobile ;
                    dbp:length ?length ;
                    dbp:weight ?weight .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testFilmsBudgetGross() {
        assertEquals(0.9141288999875634, query1Group1Var("films.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?budget, ?gross) AS ?corr)
                WHERE {
                    ?film a dbo:Film ;
                    dbo:budget ?budget ;
                    dbo:gross ?gross .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testCitiesPopulationElevation() {
        assertEquals(0.03726341325870183, query1Group1Var("cities.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?population, ?elevation) AS ?corr)
                WHERE {
                    ?city a dbo:City ;
                    dbo:populationTotal ?population ;
                    dbo:elevation ?elevation .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testMountainsElevationProminence() {
        assertEquals(0.3246861175417902, query1Group1Var("mountains.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?elevation, ?prominence) AS ?corr)
                WHERE {
                    ?mountain a dbo:Mountain ;
                    dbo:elevation ?elevation ;
                    dbo:prominence ?prominence .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testCountriesHdiGini() {
        assertEquals(-0.3300765425193157, query1Group1Var("countries.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?hdi, ?gini) AS ?corr)
                WHERE {
                    ?country a dbo:Country ;
                    dbp:hdi ?hdi ;
                    dbp:gini ?gini .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testAirportsElevationRunway() {
        assertEquals(0.0964522728935974, query1Group1Var("airports.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?elevation, ?runway) AS ?corr)
                WHERE {
                    ?airport a dbo:Airport ;
                    dbo:elevation ?elevation ;
                    dbp:r1LengthF ?runway .
                }
                """, "corr").floatValue(), 0.001);
    }

    @Test
    public void testUniversitiesStudentsEndowment() {
        assertEquals(0.05827686194515392, query1Group1Var("universities.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?students, ?endowment) AS ?corr)
                WHERE {
                    ?university a dbo:University ;
                    dbo:numberOfStudents ?students ;
                    dbo:endowment ?endowment .
                }
                """, "corr").floatValue(), 0.001);
    }
}
