package com.uszkaisandor.bored.leisure.data.mapper

import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import org.junit.Assert.assertEquals
import org.junit.Test

class LeisureActivityMappersTest {

    @Test
    fun `toDomain maps every field and parses the type name`() {
        val entity = LeisureActivityEntity(
            id = "42",
            name = "Bake bread",
            type = "COOKING",
            participants = 2,
            accessibility = 0.4f,
            priceRange = 0.1f,
            link = "https://example.com",
            isFavourite = true,
        )

        val domain = entity.toDomain()

        assertEquals("42", domain.id)
        assertEquals("Bake bread", domain.name)
        assertEquals(LeisureActivityType.COOKING, domain.type)
        assertEquals(2, domain.participants)
        assertEquals(0.4f, domain.accessibility)
        assertEquals(0.1f, domain.priceRange)
        assertEquals("https://example.com", domain.link)
        assertEquals(true, domain.isFavourite)
    }
}
