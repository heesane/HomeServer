export interface JsonPlaceHolder {
  userId: number;
  id: number;
  title: string;
  body: string;
}

export interface ThemeContextProps {
  theme: Theme;
  toggleTheme: () => void;
}

export interface FetcherOption extends RequestInit {
  headers?: Record<string, string>;
}

export interface ProjectId{
  id: number;
}