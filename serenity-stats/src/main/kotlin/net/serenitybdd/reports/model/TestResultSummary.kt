package net.serenitybdd.reports.model

class TestResultSummary(val totalCount: Int,
                        val countByResult: Map<String, Int>,
                        val percentageByResult: Map<String, Int>,
                        val totalTestDuration: String,
                        val clockTestDuration: String,
                        val averageTestDuration: String,
                        val maxTestDuration: String,
                        val minTestDuration: String
                        )


class PieChartProgress(val startDegrees : Int, val deltaDegrees : Int)