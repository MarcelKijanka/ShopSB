package com.marcelkijanka.shopsb.util

import java.time.LocalDate
//zdecydowanie polecam w takich funkcjach zwracanie wartości opakowanej w wrapper inforujący o potencjalnym błędzie, a nie rzucanie wyjątku (przykład napisałem w `ItemsService`)
fun String.toLocalDate() = LocalDate.parse(this, Objects.dateFormat)