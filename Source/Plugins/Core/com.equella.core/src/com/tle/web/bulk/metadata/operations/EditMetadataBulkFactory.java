/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0, (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.web.bulk.metadata.operations;

import com.google.inject.assistedinject.Assisted;
import com.tle.core.guice.BindFactory;
import com.tle.web.bulk.metadata.model.Modification.ModificationKeys;
import java.util.List;
import java.util.Map;

@BindFactory
public interface EditMetadataBulkFactory {
  EditMetadataBulkOperation editMetada(
      @Assisted("nodes") List<List<String>> nodes,
      @Assisted("mods") List<Map<ModificationKeys, String>> mods);
}
