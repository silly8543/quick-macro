package com.github.silly8543.reptile.common.scan;

import java.io.IOException;
import java.util.List;

/**
 * Created by silly on 2019/3/11 12:06
 */
public interface PackageScanner {
     List<String> getFullyQualifiedClassNameList() throws IOException;


}
