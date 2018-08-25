package oss.interfaces;

import oss.beans.OssMap;

/**
 * <p>
 * There are two OSS models - one for creating game templates, another for playing games (instances of templates). I
 * want to re-use some components, such as the panel that displays maps, so the models will conform to this interface,
 * and the panel only ask for the interface.
 * <p>
 * Only methods required by multiple views or controllers should included in this interface.
 */

public interface OssModel {

/**
 * @return the model's current map
 */
public OssMap getMap();

}
