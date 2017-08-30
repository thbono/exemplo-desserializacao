package br.com.db1.session;

import br.com.db1.util.DB1Json;
import br.com.db1.util.DB1Redis;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DB1SessionRest extends DB1Session {

	private String id;
	private Map<String, Wrapper> attrs;

    private DB1SessionRest(final String id) {
        this.id = Preconditions.checkNotNull(id, "Id cannot be null");
        attrs = new HashMap<>();
    }

	static DB1SessionRest getSessionOrCreateNew(final String id) {
        final Optional<String> sessionJson = Optional.fromNullable(DB1Redis.get(id));

        if (!sessionJson.isPresent()) {
            final DB1SessionRest newSession = new DB1SessionRest(UUID.randomUUID().toString());
            newSession.save();
            return newSession;
        }

        return DB1Json.fromJson(sessionJson.get(), DB1SessionRest.class);
    }

    public String getId() {
        return id;
    }

    private void save() {
        DB1Redis.set(id, DB1Json.toJson(this));
    }

    @Override
	public void put(String key, Object value) {
        attrs.put(key, new Wrapper(value));
        save();
	}

    @Override
    public void remove(String key) {
        attrs.remove(key);
        save();
    }

    @Override
	public Object get(String key) {
        final Optional<Wrapper> wrapper = Optional.fromNullable(attrs.get(key));
        return wrapper.isPresent() ? wrapper.get().unmarshal() : null;
	}

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ref", Integer.toHexString(this.hashCode()))
                .add("id", id)
                .add("attrs", attrs)
                .toString();
    }

    private class Wrapper {
        private String type;
        private String value;

        private Wrapper(final Object object) {
            Preconditions.checkNotNull(object, "Object cannot be null");
            type = object.getClass().getCanonicalName();
            value = DB1Json.toJson(object);
        }

        private Object unmarshal() {
            try {
                return DB1Json.fromJson(value, Class.forName(type));
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(String.format("Class %s not found", type), ex);
            }
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("type", type)
                    .add("value", value)
                    .toString();
        }
    }

}
