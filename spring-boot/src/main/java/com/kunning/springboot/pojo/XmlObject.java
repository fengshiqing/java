package com.kunning.springboot.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("XmlObject")
public class XmlObject {

    @XStreamAlias("param_1")
    private String param_1;

    @XStreamAlias("param_2")
    private String param_2;

    @XStreamAlias("param_3")
    private String param_3;

    @XStreamAlias("param_4")
    private String param_4;

}
