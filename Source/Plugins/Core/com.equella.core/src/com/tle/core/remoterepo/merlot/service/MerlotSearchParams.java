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

package com.tle.core.remoterepo.merlot.service;

import com.tle.beans.entity.FederatedSearch;
import com.tle.common.util.TleDate;
import java.util.Set;

public interface MerlotSearchParams {
  public enum KeywordUse {
    ALL,
    ANY,
    EXACT_PHRASE
  }

  KeywordUse getKeywordUse();

  String getQuery();

  FederatedSearch getMerlotSearch();

  String getCategory();

  String getCommunity();

  String getLanguage();

  String getMaterialType();

  String getTechnicalFormat();

  String getMaterialAudience();

  String getSort();

  boolean isCost();

  boolean isCreativeCommons();

  Set<String> getMobileOS();

  Set<String> getMobileType();

  TleDate getCreatedBefore();

  TleDate getCreatedAfter();
}
