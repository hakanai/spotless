/*
 * Copyright 2020-2021 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.spotless.maven.groovy;

import org.junit.jupiter.api.Test;

import com.diffplug.spotless.maven.MavenIntegrationHarness;

class GrEclipseTest extends MavenIntegrationHarness {

	@Test
	void testEclipse() throws Exception {
		writePomWithGroovySteps(
				"<greclipse>",
				"  <file>${basedir}/greclipse.properties</file>",
				"  <version>4.12.0</version>",
				"</greclipse>");
		setFile("greclipse.properties").toResource("groovy/greclipse/format/greclipse.properties");

		String path = "src/main/groovy/test.groovy";
		setFile(path).toResource("groovy/greclipse/format/unformatted.test");
		mavenRunner().withArguments("spotless:apply").runNoError();
		assertFile(path).sameAsResource("groovy/greclipse/format/formatted.test");
	}
}
