package com.yourgroup.module.basics.repository

import com.yourgroup.module.basics.domain.Sample
import org.springframework.stereotype.Repository

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
@Repository
interface SampleRepository {
    fun selectOne(id: Long): Sample
}