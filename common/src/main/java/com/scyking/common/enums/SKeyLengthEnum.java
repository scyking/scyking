package com.scyking.common.enums;

import lombok.Getter;

/**
 * 秘钥对长度枚举
 */
@Getter
public enum SKeyLengthEnum {
    _128(128), _192(192), _256(256);

    private int length;

    SKeyLengthEnum(int length) {
        this.length = length;
    }
}
