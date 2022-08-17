/*
 * Copyright 2008-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.sample.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import org.springframework.batch.item.Chunk;

/**
 * Tests for {@link RetrySampleItemWriter}.
 *
 * @author Robert Kasanicky
 * @author Mahmoud Ben Hassine
 */
class RetrySampleItemWriterTests {

	private final RetrySampleItemWriter<Object> processor = new RetrySampleItemWriter<>();

	/*
	 * Processing throws exception on 2nd and 3rd call.
	 */
	@Test
	void testProcess() throws Exception {
		Object item = null;
		processor.write(Chunk.of(item));

		assertThrows(RuntimeException.class, () -> processor.write(Chunk.of(item, item, item)));

		processor.write(Chunk.of(item));

		assertEquals(5, processor.getCounter());
	}

}
