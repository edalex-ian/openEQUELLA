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

package com.tle.web.api.drm

import com.tle.beans.item.DrmSettings
import com.tle.web.viewitem.I18nDRM
import scala.collection.JavaConverters._

case class DrmParties(title: String, partyList: List[String])

case class DrmTerms(terms: Option[String],
                    regularPermission: Option[String],
                    additionalPermission: Option[String],
                    educationSector: Option[String],
                    parties: Option[DrmParties])

object DrmTerms {
  def buildPermissionText(permissions: String, title: String): Option[String] = {
    if (permissions.nonEmpty) Option(s"$title $permissions") else None
  }

  def apply(drmSettings: DrmSettings): DrmTerms = {
    val drmI18n = new I18nDRM(drmSettings)

    val terms = Option(drmI18n.getTerms) match {
      case Some(t) if t.trim.nonEmpty => Option(s"${drmI18n.getTermsText} \n $t")
      case _                          => None
    }
    val regularPermission =
      buildPermissionText(drmI18n.getPermissions1List, drmI18n.getItemMayFreelyBeText)
    val additionalPermission =
      buildPermissionText(drmI18n.getPermissions2List, drmI18n.getAdditionallyUserMayText)

    val educationSector =
      if (drmI18n.isUseEducation) Option(drmI18n.getEducationSectorText) else None

    val parties = if (drmI18n.isAttribution && !drmI18n.getParties.isEmpty) {
      Option(
        DrmParties(drmI18n.getAttributeOwnersText,
                   drmI18n.getParties.asScala.map(p => s"${p.getName} ${p.getEmail}").toList))
    } else {
      None
    }

    DrmTerms(terms, regularPermission, additionalPermission, educationSector, parties)
  }
}
