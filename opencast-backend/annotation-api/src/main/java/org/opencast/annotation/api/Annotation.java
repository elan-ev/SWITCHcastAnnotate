/**
 *  Copyright 2012, Entwine GmbH, Switzerland
 *  Licensed under the Educational Community License, Version 2.0
 *  (the "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *  http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 *
 */

package org.opencast.annotation.api;

import org.opencastproject.util.data.Option;

/** Class representing an annotation. */
public interface Annotation extends Resource {

  /** The track id where the annotation is */
  long getTrackId();

  /** The annotation text */
  Option<String> getText();

  /** The annotation entry timepoint in seconds. */
  double getStart();

  /** The duration of the annotation in seconds. */
  Option<Double> getDuration();

  /** The annotation settings */
  Option<String> getSettings();

  /** The label that is used for this annotation */
  Option<Long> getLabelId();

  /** The scale value that is used for this annotation */
  Option<Long> getScaleValueId();
}
