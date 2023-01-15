package info.developia.opia.domain.scope;

import info.developia.opia.domain.type.Type;

/**
 * Created by kuba on 13.05.16.
 */
public interface Variable {
    Type getType();

    String getName();
}
