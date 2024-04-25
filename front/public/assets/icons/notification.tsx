import React from 'react'

type Fill = {
  color: string;
}

const notification = ({ color }: Fill) => (
  <svg width="64" height="65" viewBox="0 0 64 65" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
      d="M30.5 55.5C33.1812 55.5 35.375 53.2846 35.375 50.5769H25.625C25.625 53.2846 27.7944 55.5 30.5 55.5ZM45.125 40.7308V28.4231C45.125 20.8662 41.1275 14.54 34.1562 12.8662V11.1923C34.1562 9.14923 32.5231 7.5 30.5 7.5C28.4769 7.5 26.8438 9.14923 26.8438 11.1923V12.8662C19.8481 14.54 15.875 20.8415 15.875 28.4231V40.7308L11 45.6538V48.1154H50V45.6538L45.125 40.7308Z"
      fill={color} />
  </svg>
)

export default notification;