"use client";

import React from "react";
import { useTheme } from "@/components/themeContext";
import SunIcon from "@/components/ui/icon/sunIcon";
import MoonIcon from "@/components/ui/icon/moonIcon";

export const ThemeToggleButton = () => {
    const { theme, toggleTheme } = useTheme();

    return (
        <div className="fixed bottom-4 right-4"> {/* 위치를 고정하기 위해 fixed 클래스와 위치 조정을 추가 */}
            <button
                onClick={toggleTheme}
                className="p-2 rounded-md bg-gray-200 dark:bg-gray-800"
            >
                {theme === "light" ? <SunIcon className="h-5 w-5" /> : <MoonIcon className="h-5 w-5" />}
            </button>
        </div>
    );
};
