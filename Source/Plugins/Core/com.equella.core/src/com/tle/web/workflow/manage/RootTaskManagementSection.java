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

package com.tle.web.workflow.manage;

import com.tle.web.search.base.ContextableSearchSection;
import com.tle.web.sections.InfoCreator;
import com.tle.web.sections.SectionInfo;
import com.tle.web.sections.equella.annotation.PlugKey;
import com.tle.web.sections.equella.layout.ContentLayout;
import com.tle.web.sections.render.Label;

public class RootTaskManagementSection
    extends ContextableSearchSection<ContextableSearchSection.Model> {
  @PlugKey("manage.title")
  private static Label LABEL_TITLE;

  public static String URL = "/access/managetasks.do";

  @SuppressWarnings("nls")
  @Override
  protected String getSessionKey() {
    return "$MANAGE_KEY$";
  }

  @Override
  public Label getTitle(SectionInfo info) {
    return LABEL_TITLE;
  }

  @SuppressWarnings("nls")
  public static SectionInfo create(InfoCreator creator) {
    return creator.createForward(URL);
  }

  @Override
  protected ContentLayout getDefaultLayout(SectionInfo info) {
    return ContentLayout.TWO_COLUMN;
  }

  @Override
  protected String getPageName() {
    return URL;
  }
}
