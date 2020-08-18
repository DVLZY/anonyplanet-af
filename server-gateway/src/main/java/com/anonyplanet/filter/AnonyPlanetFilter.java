package com.anonyplanet.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

/**
 * @author 高祎蒙
 */
@Component
public class AnonyPlanetFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
