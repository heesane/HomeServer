"use client";

import React from "react";
import { useTheme } from "@/components/themeContext";
import SunIcon from "@/components/ui/icon/sunIcon";
import MoonIcon from "@/components/ui/icon/moonIcon";

export const ThemeToggleButton = () => {
    const { theme, toggleTheme } = useTheme();

    return (
      <div className="fixed right-4 bottom-4 text-sm"> {/* fixed 위치를 조정 */}
        <button
          onClick={toggleTheme}
          className="flex items-center gap-2 px-4 py-2 rounded-md bg-gray-200 dark:bg-gray-800 text-black dark:text-white shadow-lg transition-colors duration-200"
        >
          {/* 아이콘 */}
          {theme === "light" ? (
            <SunIcon className="h-5 w-5"/>
          ) : (
            <MoonIcon className="h-5 w-5"/>
          )}
        </button>
      </div>

    );
};
