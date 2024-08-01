package wxdgaming.mmo.core.struct;

import lombok.Getter;

/**
 * 功能id
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-27 21:22
 **/
@Getter
public class TypeBase {

    private final int code;
    private final String comment;

    public TypeBase(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeBase that = (TypeBase) o;

        return code == that.code;
    }

    @Override public int hashCode() {
        return code;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{");
        sb.append("code=").append(code);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
